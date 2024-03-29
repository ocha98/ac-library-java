import argparse
import os
import shutil
import base64
import tempfile
import random
import string
import subprocess
import glob

code_template = """// This code is generated by embedder.py [AC Library Java](https://github.com/ocha98/ac-library-java)
/* Original source code:
{origin_src_code}
*/

class Main {{
    public static void main(String[] args) {{
        StringBuilder sb = new StringBuilder();
        sb.ensureCapacity({len_base64});
        sb{base64_jar};
        String encodedString = sb.toString();
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(encodedString);

        String filePath = "/tmp/{random_str}/main.jar";
        java.io.File file = new java.io.File(filePath);
        java.io.File parentDir = file.getParentFile();
        parentDir.mkdirs();
        
        try ( java.io.OutputStream os = new java.io.FileOutputStream(file)) {{
            os.write(decodedBytes);
        }} catch (java.io.IOException e) {{
            e.printStackTrace();
        }}

        try {{
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", filePath);
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
        }} catch (java.io.IOException | InterruptedException e) {{
            e.printStackTrace();
        }}
    }}
}}
"""

def generate_random_str(n: int):
   randlst = [random.choice(string.ascii_letters + string.digits) for i in range(n)]
   return ''.join(randlst)

def compile_src(java_src_path: str, jar_path: str, target:str|None = None):
    if target is None:
        subprocess.run(['javac', '-cp', jar_path, java_src_path], check=True)
    else:
        subprocess.run(['javac', '-cp', jar_path, '--release', target, java_src_path], check=True)

def extract_jar(jar_file_path: str):
    subprocess.run(['jar', 'xf', jar_file_path], check=True)
    shutil.rmtree("META-INF")

def create_jar(file_name: str, mainifest_path: str, classes: list[str]):
    subprocess.run(['jar', 'cfm', file_name, mainifest_path] + classes, check=True)

def long_string_to_string_builder(s: str, chunck: int = 65000) -> str:
    split_s = []
    for i in range(0, len(s), chunck):
        split_s.append(s[i:i+chunck])

    retu = ''
    for s in split_s:
        retu += f'.append("{s}")'

    return retu
    
def create_embeded_code(jar_file_path: str, origin_src_code: str) -> str:
    with open(jar_file_path, 'rb') as binary_file:
        base64_jar =  base64.b64encode(binary_file.read()).decode()
    splited_base64_jar = long_string_to_string_builder(base64_jar)

    return code_template.format(
                origin_src_code = origin_src_code,
                len_base64      = len(base64_jar),
                base64_jar      = splited_base64_jar, 
                random_str      = generate_random_str(10) 
            )

def embed(src_path: str, jar_path: str, target: str|None = None) -> str:
    original_dir = os.getcwd()
    tmp_dir = tempfile.mkdtemp()
    try:
        shutil.copy(src_path, os.path.join(tmp_dir, os.path.basename(src_path)))
        shutil.copy(jar_path, os.path.join(tmp_dir, os.path.basename(jar_path)))

        os.chdir(tmp_dir)
        src_path = os.path.basename(src_path)
        jar_path = os.path.basename(jar_path)

        compile_src(src_path, jar_path, target)
        extract_jar(jar_path)
        with open("mainifest.txt", "w") as f:
            f.write("Manifest-Version: 1.0\nMain-Class: Main\n")
        class_files = glob.glob("ac_library/*.class")
        class_files.append('Main.class')
        create_jar('single.jar', 'mainifest.txt', class_files)
        
        with open(src_path) as f:
            origin_src = f.read()
        return create_embeded_code('single.jar', origin_src)        
    finally:
        os.chdir(original_dir)
        shutil.rmtree(tmp_dir)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Embedding ac_library as a jar binary.')
    parser.add_argument('src', help='Source file.')
    parser.add_argument('-c', action='store_true', help='Output to console. Default false')
    parser.add_argument('-o', default='Out.java', help='place the output into <file>. Default Out.java')
    parser.add_argument('-l', default='ac_library.jar', help='place the ac_library.jar. Default ac_library.jar')
    parser.add_argument('-t', default='17', help='target java release. This must be at least the target version of ac_library.jar.')
    
    args = parser.parse_args()
    src_path = args.src
    jar_path = args.l
    save_path = args.o
    target = args.t
    out = embed(src_path, jar_path, target)
    if args.c:
        print(out)
    else:
        with open(save_path, "w+") as f:
            f.write(out)

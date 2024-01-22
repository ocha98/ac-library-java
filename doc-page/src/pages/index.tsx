import { GetStaticProps } from "next";
import fs from 'fs';
import path from 'path'; // 追加
import { Container } from "react-bootstrap";
import Head from "next/head";
import Link from "next/link";

export default function Home({ categories }: { categories: { [key: string]: string[] } }) {
  return (
    <Container fluid='md'>
      <Head>
        <title>ac library java</title>
      </Head>
      <h1>AC Library Java Document</h1>

      <h2 style={{ borderBottom: "0.1rem solid" }}>How to install</h2>
      <p style={{fontSize: "1.3rem" }}>
        <Link href="/install_guide">Install Guide</Link>
      </p>

      <h2 style={{ borderBottom: "0.1rem solid" }}>List</h2>
      {
        Object.entries(categories).map(([category, slugs]) => (
          <div key={category}>
            <h3>{category}</h3>
            <ul>

            {
              slugs.map((slug) => (
                <li key={slug} style={{ margin: "0.2rem 0 0.2rem 0", fontSize: "1.3rem" }}>
                  <Link href={`/${category}/${slug}`}>{slug}</Link>
                </li>
              ))
            }
            </ul>
          </div>
        ))
      }
    </Container>
  )
}

export const getStaticProps: GetStaticProps = async () => {
  const baseDir = "../doc";
  const categories: { [key: string]: string[] } = {};

  const processFiles = (dir: string, currentCategory: string = "other") => {
    const fileNames = fs.readdirSync(dir);

    fileNames.forEach((fileName) => {
      const filePath = path.join(dir, fileName);
      const isDirectory = fs.statSync(filePath).isDirectory();
      if(isDirectory) return;
      const slug = fileName.replace(/\.md$/, '');
      if (!categories[currentCategory]) {
        categories[currentCategory] = [];
      }
      categories[currentCategory].push(slug);
    });
  };

  const files = fs.readdirSync(baseDir);
  files.forEach((file) => {
    const filePath = path.join(baseDir, file);
    const isDirectory = fs.statSync(filePath).isDirectory();
    if(isDirectory) {
      processFiles(filePath, file);
    } 
  });

  return {
    props: {
      categories,
    }
  };
};

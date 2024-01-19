import Markdown from 'react-markdown'
import fs from 'fs'
import { GetStaticPaths, GetStaticProps } from "next";
import path from 'path';
import { Container } from 'react-bootstrap';
import remarkMath from 'remark-math';
import rehypeKatex from 'rehype-katex';
import remarkGfm from 'remark-gfm';
import Head from 'next/head';
import SyntaxHighlighter from 'react-syntax-highlighter'
import { github } from 'react-syntax-highlighter/dist/cjs/styles/hljs'
import { ParsedUrlQuery } from 'querystring';

export const Page = ({ content, slug }: { content: string, slug: string }) => {
  return (
    <Container className='py-1' fluid='md'>
        <Head>
          <title>{ `${slug} - ac library java` }</title>
        </Head>
        <Markdown
          className='markdown'
          remarkPlugins={[remarkMath, remarkGfm]}
          rehypePlugins={[rehypeKatex]}
          components={{
              code(props) {
                const {children, className, node, ...rest} = props
                const match = /language-(\w+)/.exec(className || '')
                return match ? (
                  <SyntaxHighlighter
                    PreTag="div"
                    language={match[1]}
                    style={github}
                  >
                  {String(children).replace(/\n$/, '')}
                  </SyntaxHighlighter>
                ) : (
                  <code {...rest} className={className}>
                    {children}
                  </code>
                )
              }
            }}
        >
            {content}
        </Markdown>
    </Container>
  )
}

interface Props {
  category: string;
  slug: string;
  content: string;
}

interface Params extends ParsedUrlQuery {
  category: string;
  slug: string;
}

export const getStaticProps: GetStaticProps<Props, Params> = async ({ params }) => {
  if (!params) {
      return { notFound: true };
  }
  
  const filePath = path.join("../doc", params.category, `${params.slug}.md`);
  const content = fs.readFileSync(filePath, 'utf8');
  return {
      props: {
        slug: params.slug,
        category: params.category,
        content
      },
  };
};

const baseDir = "../doc";

const getCategories = () => {
  return fs.readdirSync(baseDir, { withFileTypes: true })
    .filter((dirent) => dirent.isDirectory())
    .map((categoryDir) => categoryDir.name);
};

const getCategoryPaths = (category: string) => {
  const categoryPath = path.join(baseDir, category);
  const categoryFiles = fs.readdirSync(categoryPath);
  return categoryFiles
    .filter((fileName) => fileName.endsWith('.md'))
    .map((fileName) => ({
    params: {
      category: category,
      slug: fileName.replace(/\.md$/, ''),
    },
  }));
};

export const getStaticPaths: GetStaticPaths = async () => {
  const categories = getCategories();

  const paths = categories.flatMap((category) => getCategoryPaths(category));

  return {
    paths,
    fallback: false,
  };
};

export default Page;
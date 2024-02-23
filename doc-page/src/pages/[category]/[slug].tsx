import fs from 'fs'
import { GetStaticPaths, GetStaticProps } from 'next'
import path from 'path'
import { Container } from 'react-bootstrap'
import Head from 'next/head'
import Markdown from '@/components/Markdown'
import { ParsedUrlQuery } from 'querystring'

export const Page = ({ content, slug }: { content: string, slug: string }) => {
  return (
    <Container className='py-1' fluid='md'>
        <Head>
          <title>{ `${slug} - ac library java` }</title>
        </Head>
        <Markdown content={content} />
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
  
  const filePath = path.join('../doc', params.category, `${params.slug}.md`);
  const content = fs.readFileSync(filePath, 'utf8');
  return {
      props: {
        slug: params.slug,
        category: params.category,
        content
      },
  };
};

const baseDir = '../doc'

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
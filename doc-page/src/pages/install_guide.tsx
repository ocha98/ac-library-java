import Markdown from '@/components/Markdown'
import fs from 'fs'
import { GetStaticProps } from 'next'
import path from 'path'
import { Container } from 'react-bootstrap'
import Head from 'next/head'
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
  content: string;
}

interface Params extends ParsedUrlQuery {}

export const getStaticProps: GetStaticProps<Props, Params> = async () => {
  const filePath = path.join("../doc/install_guide.md");
  const content = fs.readFileSync(filePath, 'utf8');
  return {
      props: {
        content
      },
  };
};


export default Page;
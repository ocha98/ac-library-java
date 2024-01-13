import Markdown from 'react-markdown'
import fs from 'fs'
import { GetStaticPaths, GetStaticProps } from "next";
import path from 'path';
import { Container } from 'react-bootstrap';
import remarkMath from 'remark-math';
import rehypeKatex from 'rehype-katex';
import remarkGfm from 'remark-gfm';
import Head from 'next/head';

export const Page = ({ content, slug }: { content: string, slug: string }) => {
    return (
        <Container className='p-5'>
            <Head>
                <title>{ `${slug} - ac library java` }</title>
            </Head>
            <Markdown
                remarkPlugins={[remarkMath, remarkGfm]}
                rehypePlugins={[rehypeKatex]}
            >
                {content}
            </Markdown>
        </Container>
    )
}

export const getStaticProps: GetStaticProps = async ({ params }) => {
    if(!params) {
        return { notFound: true }
    }
    const filePath = path.join("../doc", `${params.slug}.md`)
    const content =  fs.readFileSync(filePath, 'utf8')
    return {
        props: {
            slug: params.slug,
            content
        }
    }
}

export const getStaticPaths: GetStaticPaths = async () => {
    // ../doc/にある.mdファイルの一覧を取得する
    const fileNames = fs.readdirSync("../doc")
    const slugs =  fileNames.map((fileNmae) => { return fileNmae.replace(/\.md$/, '') })
    const paths = slugs.map(slug => {
        return {
          params: {
            slug
          }
        }
      })

    return {
        paths,
        fallback: false,
    };
}

export default Page;
import { GetStaticProps } from "next";
import fs from 'fs'
import { Container } from "react-bootstrap";
import Head from "next/head";
import Link from "next/link";

export default function Home({ slugs }: { slugs: string[] }) {
  return (
    <Container fluid='md'>
      <Head>
        <title>ac library java</title>
      </Head>
      <h1>AC Library Java Document</h1>
      {
        slugs.map((slug) => {
          return (
            <div key={slug}>
              <Link href={`/${slug}`}>{slug}</Link>
            </div>
          )
        })
      }
    </Container>
  )
}


export const getStaticProps: GetStaticProps = async () => {
  // ../doc/にある.mdファイルの一覧を取得する
  const fileNames = fs.readdirSync("../doc")
  const slugs =  fileNames.map((fileNmae) => { return fileNmae.replace(/\.md$/, '') })

  return {
    props: {
      slugs,
    }
  }
}
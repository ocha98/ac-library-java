import { GetStaticProps } from 'next'
import { Container } from 'react-bootstrap'
import Head from 'next/head'
import { ParsedUrlQuery } from 'querystring'
import { LANGS, getContentPath } from '@/lib/utilServer'
import { ContentViewer } from '@/components/ContentViewer'
import { getLang } from '@/lib/utilClient'

export const Page = ({ content }: { content: Content, lang: string }) => {
  const lang = getLang()
  return (
    <Container className='py-1' fluid='md'>
        <Head>
          <title>{ `Embedder - ac library java` }</title>
        </Head>
        <ContentViewer content={content} lang={lang} />
    </Container>
  )
}

interface Props {
  content: Content
}

interface Params extends ParsedUrlQuery {
  lang: string
}

export const getStaticProps: GetStaticProps<Props, Params> = async ({ params }) => {
  if (!params) {
    return { notFound: true };
  }

  const content = getContentPath('embedder.md')
  return {
      props: {
        content,
        lang: params.lang
      },
  }
}

export const getStaticPaths = async () => {
  const paths = LANGS.map(lang => ({ params: { lang } }))
  return {
    paths,
    fallback: false
  }
}


export default Page

import { GetStaticPaths, GetStaticProps } from 'next'
import { Container } from 'react-bootstrap'
import Head from 'next/head'
import { ParsedUrlQuery } from 'querystring'
import { getSlugsByCategory, getCategories, getContent, LANGS } from '@/lib/utilServer'
import { ContentViewer } from '@/components/ContentViewer'
import { getLang } from '@/lib/utilClient'

export const Page = ({ content, slug }: Props) => {
  const lang = getLang()
  if (typeof lang !== 'string') {
    throw new Error('lang is not string')
  }
  return (
    <Container className='py-1' fluid='md'>
      <Head>
        <title>{`${slug} - ac library java`}</title>
      </Head>
      <ContentViewer content={content} lang={lang} />
    </Container>
  )
}

interface Props {
  category: string
  slug: string
  content: Content
}

interface Params extends ParsedUrlQuery {
  category: string
  slug: string
  lang: string
}

export const getStaticProps: GetStaticProps<Props, Params> = async ({ params }) => {
  if (!params) {
    return { notFound: true };
  }

  const content = getContent(params.category, params.slug)
  return {
    props: {
      slug: params.slug,
      category: params.category,
      content
    },
  }
}

export const getStaticPaths: GetStaticPaths = async () => {
  const paths: { params: Params }[] = [];
  for (const category of getCategories()) {
    for (const slug of getSlugsByCategory(category)) {
      for (const lang of LANGS) {
        paths.push({ params: { lang, category, slug } })
      }
    }
  }

  return {
    paths,
    fallback: false,
  };
};

export default Page

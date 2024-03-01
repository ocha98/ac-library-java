import { GetStaticProps } from 'next';
import { LANGS, getCategories, getSlugsByCategory } from '@/lib/utilServer';
import { Container } from 'react-bootstrap';
import Head from 'next/head';
import Link from 'next/link';
import urlJoin from 'url-join';
import { getLang } from '@/lib/utilClient';

export default function Home({ categories }: { categories: { [key: string]: string[] } }) {
  const lang = getLang()

  const url = (slug: string) => urlJoin('/', lang, slug);

  return (
    <Container fluid='md'>
      <Head>
        <title>ac library java</title>
      </Head>
      <h1>AC Library Java Document</h1>

      <h2 style={{ borderBottom: '0.1rem solid' }}>How to install</h2>
      <p style={{fontSize: '1.3rem' }}>
        <Link href={url('/install_guide')}>Install Guide</Link>
      </p>

      <h2 style={{ borderBottom: '0.1rem solid' }}>Embedder</h2>
      <p style={{fontSize: '1.3rem' }}>
        <Link href={url('/embedder')}>Embedder</Link>
      </p>

      <h2 style={{ borderBottom: '0.1rem solid' }}>List</h2>
      {
        Object.entries(categories).map(([category, slugs]) => (
          <div key={category}>
            <h3>{category}</h3>
            <ul>

            {
              slugs.map((slug) => (
                <li key={slug} style={{ margin: '0.2rem 0 0.2rem 0', fontSize: '1.3rem' }}>
                  <Link href={url(`/${category}/${slug}`)}>{slug}</Link>
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
  const categories: {[key: string]: string[]} = {};
  
  for(const category of getCategories()) {
    categories[category] = getSlugsByCategory(category);
  }

  return {
    props: {
      categories,
    }
  }
}

export const getStaticPaths = async () => {
  const paths = LANGS.map(lang => ({ params: { lang } }))
  return {
    paths,
    fallback: false
  }
}
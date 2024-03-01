import 'bootstrap/dist/css/bootstrap.min.css'
import 'katex/dist/katex.min.css'
import type { AppProps } from 'next/app'
import Link from 'next/link';
import Container from 'react-bootstrap/Container'
import Nav from 'react-bootstrap/Nav'
import Navbar from 'react-bootstrap/Navbar'
import '../styles/markdown.css'
import { NavDropdown } from 'react-bootstrap';
import { getLang } from '@/lib/utilClient';

export default function App({ Component, pageProps }: AppProps) {
  const lang = getLang()
  
  return(
    <>
      <Navbar expand='lg' className='bg-body-tertiary'>
        <Container>
          <Link href={`/${lang}`} legacyBehavior>
            <Navbar.Brand>AC Library Java</Navbar.Brand>
          </Link>
          <Nav className='me-auto'>
            <Nav.Link href='https://github.com/ocha98/ac-library-java'>GitHub</Nav.Link>
          </Nav>
          <NavDropdown title='Languages'>
              <NavDropdown.Item>
              <Link href='/en'>
                English
            </Link>
                </NavDropdown.Item>
              <NavDropdown.Item>
            <Link href='/jp'>
                Japanese
            </Link>
                </NavDropdown.Item>
          </NavDropdown>
        </Container>
      </Navbar>
      <Component {...pageProps} />
    </>
  ) 
}

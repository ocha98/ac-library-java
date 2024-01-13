import 'bootstrap/dist/css/bootstrap.min.css'
import 'katex/dist/katex.min.css'
import type { AppProps } from 'next/app'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

export default function App({ Component, pageProps }: AppProps) {
  return(
    <>
      <Navbar expand="lg" className="bg-body-tertiary">
        <Container>
          <Navbar.Brand href="/">ac library java</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link href="https://github.com/ocha98/ac-library-java">GitHub</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <Component {...pageProps} />
    </>
  ) 
}

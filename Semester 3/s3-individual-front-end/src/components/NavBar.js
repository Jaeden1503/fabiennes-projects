import React, { useState, useEffect } from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import TokenManager from '../apis/TokenManager';
import '../css/Navbar.css'
import { useLocation } from 'react-router-dom';

function NavBar() {
  const [isLoggedIn, setisLoggedIn] = useState(null);
  const [claims, setClaims] = useState(TokenManager.getClaims());
  const {pathname} = useLocation();
  const withouSidebarRoutes = ["/qnasession"];
   
  const logIn = () => {
    if (claims != null) {
      setisLoggedIn(true);
    }
    else
      setisLoggedIn(false);
  };
   
  const logOut = () => {
    setisLoggedIn(false);
    TokenManager.clear();
    setClaims(null);
  };

  
  useEffect(() => {  
    logIn();
  }, []);
  
  
  
  if (withouSidebarRoutes.some((item) => pathname.includes(item))) { 
    return null
  };
  return (
    <Navbar className="navbar" variant="dark">
      <Container>
        <Navbar.Brand href="/">Find Your Garageband</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto"></Nav>
          <Nav>
            {isLoggedIn ? <Nav.Link href="/profile">Profile</Nav.Link> : null}
            
            <Nav.Link className='navbar-spacing' href="/search">Search</Nav.Link>
            <Nav.Link className='navbar-spacing' href="/qna">QnA</Nav.Link>
            {isLoggedIn ? 
              <Nav.Link className='navbar-spacing' onClick={logOut} href="/">Logout</Nav.Link>
              : <Nav.Link className='navbar-spacing' href="/login">Login/Register</Nav.Link>
            }
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
  
export default NavBar;
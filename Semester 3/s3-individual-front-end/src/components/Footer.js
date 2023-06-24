import React from 'react'
import '../css/Footer.css'
import { useLocation } from 'react-router-dom';

function Footer() {
  const {pathname} = useLocation();
  const withouSidebarRoutes = ["/qnasession"];

  if (withouSidebarRoutes.some((item) => pathname.includes(item))) { 
    return null
  };
  return (
    <div className='footer copyright'>
      <p>© <span>2023 | </span>Find your garageband | <a href="/about" className="transition">About</a></p>
    </div>
  )
}

export default Footer
import React, { useState, useEffect } from 'react'
import TokenManager from '../apis/TokenManager';
import { useNavigate } from 'react-router-dom';
import '../css/Dashboard.css'

function Topbar() {
  const navigate = useNavigate();

  const logOut = () => {
    TokenManager.clear();
    navigate("/");
    window.location.reload(false);
  };

  return (
    <div className='topbar'>
      <button className="logout-button" onClick={logOut}>Log out</button>
      <div >Find your Garageband</div>
    </div>

  )
}

export default Topbar
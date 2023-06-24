import React, { useState, useEffect } from 'react'
import AuthAPI from '../apis/AuthAPI';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faAt, faKey } from '@fortawesome/free-solid-svg-icons'
import { Card } from 'react-bootstrap';
import '../css/Background.css'
import '../css/LoginPage.css'
import homeImage from '../images/band_playing_music.png'

function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    handleLogin(email, password);
  }

  const handleLogin = (email, password) => {
    AuthAPI.login(email, password)
      .then(response => { 
        console.log(response);
        if(response.roles?.includes("ADMIN")) {
          navigate("/dashboard")
        }
        else if(response.roles?.includes("USER")) {
          navigate("/profile")
        }
        window.location.reload(false);
      })
      .catch(() => alert("Login failed!"))
      .catch(error => console.error(error));
  }

  //makes sure the boxes on the background don't cause vertical scrolling
  useEffect(() => {
    document.body.classList.add("overflow-hidden");
      return () => {
          document.body.classList.remove("overflow-hidden");
      };
  }, []);


  return (
    <div className="container">
      <div className="middle_top"></div>
      <div className="left_top"></div>
      <div className="left_bottom"></div>
      <div className="middle_center"></div>
      <div className="right_bottom"></div>
      <div className='right_bottom'><img className='img_band' src={homeImage} alt="band playing music" /></div>


      <Card className="loginCard">
        <Card.Header><h3>Sign In</h3></Card.Header>

        <Card.Body>
          <div className="input-group form-group">
            <span className="input-group-text"><FontAwesomeIcon icon={faAt} /></span> 
            <input type="email" id='email' className="form-control" onChange={(e) => setEmail(e.target.value)} placeholder="email"></input>
          </div>
          <br/>

          <div className="input-group form-group">
            <span className="input-group-text"><FontAwesomeIcon icon={faKey} /></span>
            <input type="password" id='password' className="form-control" onChange={(e) => setPassword(e.target.value)} placeholder="password"></input>
          </div>

          <button className="login-button" data-cy="submit" onClick={handleSubmit}>Login</button>
        </Card.Body>

        <Card.Footer>
          <div className="login-footer">Don't have an account? <a href="/register">Sign Up</a></div>
        </Card.Footer>
      </Card>
    </div>
  );
}

export default LoginPage
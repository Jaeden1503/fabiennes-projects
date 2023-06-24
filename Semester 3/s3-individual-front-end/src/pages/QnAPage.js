import React, { useState, useEffect } from 'react'
import TokenManager from '../apis/TokenManager';
import ChatAPI from '../apis/ChatAPI';
import { useNavigate } from 'react-router-dom';
import '../css/QnA.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faAngleRight } from '@fortawesome/free-solid-svg-icons'

function QnAPage() {
  const [chats, setChats] = useState([]);
  const [error, setError] = useState(null);
  const [title, setTitle] = useState("");
  const [isLoading, setLoading] = useState(true);
  const navigate = useNavigate();
  const claims = TokenManager.getClaims();


  const getAllChats = () => {
    ChatAPI.getAllChats()
      .then((chat) => {
        setChats(chat);
        setError(null);
        setLoading(false);

      })
      .catch((err) => setError(err));
  };

  const createChat = (event) => {
    event.preventDefault();

    if (!claims?.roles?.includes("USER") && !claims?.userId) {
      alert("you have to be logged in to create a session");
    } 
    else {
      const question = {
        starter: claims.sub,
        title: title,
      };

      ChatAPI.createChat(question)
      .then((response) => {
        console.log(response);
        //alert("chat created");
        // setTitle('');
        // navigate(`/qnasession/${response.chatId}`); //go to session page
        window.open(`http://localhost:3000/qnasession/${response.chatId}`, "_blank", "height=700,width=1200");
      })
      .catch((err) => {
        //console.log(err.response.status);
        if (err.response.status === 400) {
          alert("You can only have one active chat at a time");
        } else {
          alert("Could not create chat");
        }
      });
    }
  };


  const openInNewWindow = (chatId) => {
    // event.preventDefault();
    if (claims?.roles?.includes("USER")  && claims?.userId) {
      const joinRequest = {
        username: claims.sub,
        userId: claims.userId
      }
      
      ChatAPI.joinChat(chatId, joinRequest)
      .then((response) => {
        console.log(response);
        if (response === "Not allowed to enter chat") {
          alert("You're not allowed to enter this chat")
        }
        else {
          window.open(`http://localhost:3000/qnasession/${chatId}`, "_blank", "height=700,width=1200");
        }
      })
      .catch((err) => {
        console.log(err.response.status);
        alert("Something went wrong");
      });

    }  
    else {
      alert("You have to be logged in to enter a chat")
    }
  };

  useEffect(() => {  
    getAllChats();
  }, []);


  if (error) {
    return <div className="App">An error has occured...</div>;
  }
  else if (isLoading) {
    return <div className="App">Loading...</div>;
  }
  return (
    <div className="container question-body">
      
      <h3>Live chat with someone!</h3>
      <h5>Ask a question, or help someone else with their question!</h5>

      <div>
        <form onSubmit={createChat}>
          <input className='chat-create' required type='text' placeholder='Write a question' onChange={(e) => { setTitle(e.target.value); }}/>
          <button className="chat-create-button">Create chat</button>
        </form>
      </div>

      <div className="chat-search">
        <input className='chat-searchText' id='chat-input' type='text' placeholder='Search for a specific question'/>
      </div>

      {chats.length === 0 && !error && <p>There are no QnA sessions</p>}

      <div>
        {chats.map((chat) => (
          <div className="chat-item" key={chat.id}>
            <div className='chat-title'>{chat.title} </div>
            <div className='chat-names'>
                <div>{chat.starter}</div>
                <div style={{fontStyle: chat.joiner === null ? 'italic' : 'normal'}}>{chat.joiner === null ? "joinable" : chat.joiner}</div>
            
            </div>
            <div className='join-icon'><FontAwesomeIcon icon={faAngleRight} onClick={() => openInNewWindow(chat.id)} size="2x"/></div>

          </div>
        ))}

      </div>
    </div>
  ); 
}

export default QnAPage;
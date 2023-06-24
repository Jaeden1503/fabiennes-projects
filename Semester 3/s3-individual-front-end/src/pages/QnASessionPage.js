import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom';
import { v4 as uuidv4 } from 'uuid';
import { Client } from '@stomp/stompjs';
import TokenManager from '../apis/TokenManager';
import ChatAPI from '../apis/ChatAPI';
import SendMessagePlaceholder from '../components/SendMessagePlaceholder';
import '../css/QnASession.css';

function QnASessionPage() {
  let { id } = useParams();
  const [chat, setChat] = useState('');
  const [stompClient, setStompClient] = useState();
  const [messagesReceived, setMessagesReceived] = useState([]);
  const claims = TokenManager.getClaims();

  const getChat = () => {
    ChatAPI.getChat((id))
    .then(chatInfo => {
      setChat(chatInfo)
      console.log(chatInfo);
    })
    .catch(err => console.log(err));
  };

  const setupStompClient = () => {
    // stomp client over websockets
    const stompClient = new Client({
      brokerURL: "ws://localhost:8080/ws",
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    stompClient.onConnect = () => {
      // subscribe to the backend "private" topic
      stompClient.subscribe(`/user/${claims.sub}/queue/inboxmessages`, (data) => {
        onMessageReceived(data);
      });
    };
    // initiate client
    stompClient.activate();
    // maintain the client for sending and receiving
    setStompClient(stompClient);
  };

  // send the data using Stomp
  const sendMessage = (newMessage) => {
    var payload = {};
    
    if(claims.sub === chat.starter) {
      if(chat.joiner === null) {
        getChat(); //works but takes some time to setJoiner so, message goes to no one
      }
      
      payload = { id: uuidv4(), from: claims.sub, to: chat.joiner, text: newMessage.text };
      console.log(payload);
    }
    else {
      payload = { id: uuidv4(), from: claims.sub, to: chat.starter, text: newMessage.text };
    }
    console.log(payload);
    setMessagesReceived(messagesReceived => [...messagesReceived, payload]);

    if (payload.to) {
      stompClient.publish({ 'destination': `/user/${payload.to}/queue/inboxmessages`, body: JSON.stringify(payload) });
    }

    if(newMessage.text === "host left: chat will be deleted" || newMessage.text === "Joiner left chat") {
      window.opener = null;
      window.open("", "_self");
      window.close();
    }
  };
  
  // display the received data
  const onMessageReceived = (data) => {
    console.log(data);
    const message = JSON.parse(data.body); //message object, payload message
    console.log(message)
    if(message.text === "host left: chat will be deleted") {
      alert(message.text)
      setTimeout(3000);

      window.opener = null;
      window.open("", "_self");
      window.close();
      return;
    }
    else if (message.text === "Joiner left chat") {
        getChat();
        message.from = "system"

        setMessagesReceived(messagesReceived => [...messagesReceived, message]);
        return;
    }

    setMessagesReceived(messagesReceived => [...messagesReceived, message]);
  };

  const onClose = () => {
    if (claims?.roles?.includes("USER")  && claims?.userId) {
      const leaveRequest = {
        username: claims.sub,
        userId: claims.userId
      }
      
      ChatAPI.leaveChat(id, leaveRequest)
      .then((response) => {
        console.log(response);
        if (response.response === "Joiner left chat") {
          sendMessage({ 'text': response.response });

          window.opener = null;
          window.open("", "_self");
          window.close();
        }
        else if (response.response === "Chat finished and deleted") {
          //check if message array.length is o. -> getChat().
          //if chat.joiner !== null, set to: chat.joiner //else just delete window
          // if(messagesReceived.length === 0) {
          //   getChat();
          //   // if(chat.joiner !== null) {
          //   //   //sendMessage({ 'text': "host left: chat will be deleted" });
          //   // }
          // }

          sendMessage({ 'text': "host left: chat will be deleted" });
        }
      })
      .catch((err) => {
        console.log(err.response.status);
        alert("Something went wrong");

          window.opener = null;
          window.open("", "_self");
          window.close();
      });
    }
  };

  useEffect(() => { 
    getChat(); 
    setupStompClient();

    // if(chat.joiner !== null) {
    //   // JSON

    //   onMessageReceived({"someone joined"})
    // }
  }, []);

  
  return (
    <div className="container session-bg">
      <h3>
        You ({claims.sub}) are chatting with{" "}
        {chat.joiner === claims.sub
          ? chat.starter
          : chat.joiner === null
          ? "'no one yet'"
          : chat.joiner}{" "}
      </h3>
      <h4>about "{chat.title}"</h4>

      <div className="black-line"></div>

      {messagesReceived.map((message) => (
        <div key={message.id}>
          {/* I'm the sender, message on the right */}
          {message.from === claims.sub && <div className='message-sender'>
            <b>me:</b> {message.text}</div>}

          {message.from !== claims.sub && <div className='message-receiver'>
            <b>{message.from}:</b> {message.text}</div>}
        </div>
      ))}

      <div className='session-send'>
        <div className='black-line'></div>

        <div className='chat-buttons-grid'>
          <div>
            <button className='close-chat-button' onClick={onClose}>close chat</button>
          </div>
          <SendMessagePlaceholder onMessageSend={sendMessage} />       
        </div>

      </div>
    </div>
  );
}

export default QnASessionPage
import React, { useState, useEffect } from 'react'
import TokenManager from '../apis/TokenManager';
import UserAPI from '../apis/UserAPI'
import { Form } from "react-bootstrap";
import _isEqual from 'lodash/isEqual';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCheck, faPenToSquare } from '@fortawesome/free-solid-svg-icons'
import '../css/Dashboard.css';

const AllUsersPage = () => {
  const [users, setUsers] = useState([]);
  const [editUser, setEditUser] = useState({});
  const [fakeUser, setFakeUser] = useState({});
  const [editID, setEditId] = useState(null)
  const [error, setError] = useState(null);
  const claims = TokenManager.getClaims();

  const getAllUsers = () => {
    if(claims?.roles?.includes('ADMIN')) {
      UserAPI.getAllUsers()
      .then(users => {
        setUsers(users);
        setError(null)
        console.log(users);
      })
      .catch((err) => setError(err))
    }
    else {
        alert("Oops you don't have permission to do this")
    }
  }

  const updateUser = () => {
    // console.log(editUser);

    if (claims?.roles?.includes("ADMIN")  && claims?.userId) {
        UserAPI.updateUser(editUser.id, editUser)
          .then((response) => {
            console.log(response);
            alert("successfully updated profile");
            window.location.reload(false);
          })
          .catch((err) => {
            console.log(err.response.status);
            if (err.response.status === 400) {
                alert("username already in use");
            }
            else {
                alert("could not update profile");
            }
        });
      }
  }

  const handleEditClick = (user) => {
    if (editID === editUser.id) {
      setEditId(null);
      if (!_isEqual(fakeUser, user)) {
        updateUser();
        return;

      }
    } 
    else {
      setEditId(user.id);
      setEditUser(user);
      setFakeUser(user);
    }
  };

  useEffect(() => {
    getAllUsers();
    //console.log(posts)
  }, []);

  if (error) {
    return <div className="App">Unable to load in data... Something went wrong</div>;
  }
  return (
    <div className='content-container'>
      <h3>Users:</h3>
      <p>Here are all the users displayed, some of the users fields can be edited</p>

      <table className="admin-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>About</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
        {users.length > 0 ? (
            users.map((user) => {
            
                return(
                    <>
                      {editID === user.id ? //editing state

                        <tr key={user.id}>
                          <td>
                          <Form.Control type="text" 
                              value={editUser.username} 
                              onChange={(e)=>{setEditUser({...editUser, username:e.target.value}); setFakeUser({...fakeUser, username:e.target.value }) }}/>
                          </td>
                          <td>{user.email}</td>
                          <td>
                          <Form.Control type="text" 
                              value={editUser?.about === null? "" : editUser.about} 
                              onChange={(e)=>{setEditUser({...editUser, about:e.target.value } ); setFakeUser({...fakeUser, about:e.target.value }) }}/>
                          </td>                                      
                          <td style={{"width": "50px"}}><FontAwesomeIcon icon={faCheck} onClick={(e)=> handleEditClick(user)} 
                            style={{"color": "green",}} className='admin-user-table-icon' size="1x" />
                          </td>
                    
                        </tr>
                        :
                        // not editing
                        <tr key={user.id}>
                          <td>{user.username}</td>
                          <td>{user.email}</td>
                          <td>{user.about}</td>
                          <td style={{"width": "50px"}}><FontAwesomeIcon icon={faPenToSquare} onClick={(e)=> handleEditClick(user)} 
                            style={{"color": "red",}} className='admin-user-table-icon' size="1x" />
                          </td>
                        </tr>
                      }
                    </>
                )
            }
            
            )
        ) : (
            <tr>
                <td>...</td>
                <td>...</td>
                <td>...</td>
                <td>...</td>
            </tr>
        )}
        </tbody>

      </table>
    </div>
  )
}

export default AllUsersPage
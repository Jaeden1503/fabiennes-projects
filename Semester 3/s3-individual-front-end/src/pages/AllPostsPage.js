import React, { useState, useEffect } from 'react'
import TokenManager from '../apis/TokenManager';
import SearchPostAPI from '../apis/SearchPostAPI';
import Pagination from '../components/Pagination';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrashCan } from '@fortawesome/free-regular-svg-icons'
import '../css/Dashboard.css';

let PageSize = 5;

function AllPostsPage() {
  const [post, setPost] = useState([]);
  const [amount, setAmount] = useState(0);

  const [error, setError] = useState(null);
  const claims = TokenManager.getClaims();
  const [currentPage, setCurrentPage] = useState(1);

  const getAllSearchPosts = () => {
    console.log(currentPage)

    SearchPostAPI.getAllPostsPaginated(currentPage)
      .then(response => {
        setPost(response.searchPosts)
        setAmount(response.totalPostAmount)
        console.log(response.searchPosts)
        setError(null)
      })
      .catch(err => setError(err));
  };

  const deleteSearchPost = (id) => {
    let confirmDelete = window.confirm('Are you sure you want to delete searchpost?');
    if(confirmDelete){
      if (claims?.roles?.includes('ADMIN') && claims?.userId) {
        SearchPostAPI.deleteSearchPost(id)
        .then(response => {
          console.log(response);
          alert("searchpost deleted");
          window.location.reload(false);
        })
        .catch(err => {
          console.log(err)
          alert("couldn't delete searchpost")
        });
      }
    }
    return;
  }

  useEffect(() => {
    getAllSearchPosts();
  }, [currentPage]);

  if (error) {
    return <div className="App">Unable to load in data... Something went wrong</div>;
  }
  return (
    <div className="content-container">
      <h3>Posts:</h3>
      <p>Here are all the posts displayed, posts can be deleted</p>

      <table className="admin-table">
        <tbody>
          <tr>
            <th>Title</th>
            <th>Post description</th>
            <th>Date of post</th>
            <th>Looking for band</th>
            <th>Instrument</th>
            <th>Province</th>
            <th>Username</th>
            <th>Action</th>
          </tr>
          {post.map((post) => (
            <tr key={post.id}>
              <td>{post.title}</td>
              <td>{post.description}</td>
              <td style={{"width": "120px"}}>{post.date}</td>
              <td style={{"width": "90px"}}>{post.searchingBand === true ? "Yes" : "No"}</td>
              <td>{post.instrument}</td>
              <td>{post.province}</td>
              <td>{post.user.username}</td>
              <td style={{ width: "50px" }}>
                <FontAwesomeIcon
                  icon={faTrashCan}
                  style={{ color: "red", width: "40px", cursor: "pointer" }}
                  onClick={(e) => deleteSearchPost(post.id)}
                  size="1x"
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Pagination
        className="pagination-bar"
        currentPage={currentPage}
        totalCount={amount} //total amount of posts
        pageSize={PageSize} //amount per page
        onPageChange={page => setCurrentPage(page)}
      />
    </div>
  );
}

export default AllPostsPage
import React, { useState, useEffect } from 'react'
import SearchPostAPI from '../apis/SearchPostAPI';
import SearchPostList from '../components/SearchPostList';
import '../css/Filter.css';
import Filter from '../components/Filter';

function SearchPage() {

  const [posts, setSearchPosts] = useState([]);
  const [error, setError] = useState(null);

  const getAllSearchPosts = () => {
    SearchPostAPI.getAllSearchPosts()
      .then(searchPosts => {
        setSearchPosts(searchPosts)
        setError(null)
      })
      .catch(err => setError(err));
  };

  const filterSearchPost = (bandParam, instrumentParam, provinceParam) => {
    SearchPostAPI.filterSearchPosts(bandParam, instrumentParam, provinceParam)
    .then(searchPosts => {
      console.log(searchPosts)
      setSearchPosts(searchPosts)
      setError(null)
    })
    .catch(err => setError(err));
  }

  useEffect(() => {  
      getAllSearchPosts();
  }, []);


  return (
    <div className="container" style={{"marginBottom": "6rem"}}>

      {/* open close filter view */}
      <Filter onFilter={filterSearchPost} onNoFilter={getAllSearchPosts}/>

      {error && <p>An error has occured</p>}
      {posts.length === 0 && !error && <p data-cy="no-posts">There are no (matching) posts</p>}

      <SearchPostList searchPosts={posts} />
    </div>
  );
}

export default SearchPage
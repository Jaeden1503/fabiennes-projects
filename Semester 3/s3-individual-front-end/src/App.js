import './App.css';
import { Route, Routes, BrowserRouter as Router } from "react-router-dom"
import TokenManager from './apis/TokenManager';
import HomePage from './pages/HomePage';
import SearchPage from './pages/SearchPage';
import NavBar from './components/NavBar';
import Footer from './components/Footer';
import LoginPage from './pages/LoginPage';
import AboutPage from './pages/AboutPage';
import ProfilePage from './pages/ProfilePage';
import Protected from './components/Protected';
import PostDetailsPage from './pages/PostDetailsPage';
import RegisterPage from './pages/RegisterPage';
import CreatePostPage from './pages/CreatePostPage';
import ProfilePostDetailsPage from './pages/ProfilePostDetailsPage';
import EditProfilePage from './pages/EditProfilePage';
import QnAPage from './pages/QnAPage';
import QnASessionPage from './pages/QnASessionPage';
// admin stuff
import AdminProtected from './components/AdminProtected';
import Sidebar from './components/Sidebar'
import Statistics from './pages/Statistics';
import AllPostsPage from './pages/AllPostsPage';
import AllUsersPage from './pages/AllUsersPage';
import AllCommentsPage from './pages/AllCommentsPage';
import Topbar from './components/Topbar';

function App() {
  const claims = TokenManager.getClaims();

  if (claims?.roles?.includes("ADMIN")) {
    return (
      <div className="dashboard">
        <Sidebar />

        <div className='content-row'>
          <Router>
            <Topbar/>
            <Routes>
              <Route path="/dashboard" element={<AdminProtected> <Statistics /> </AdminProtected>}/>
              <Route path="/posts" element={ <AdminProtected> <AllPostsPage /> </AdminProtected> }/>
              <Route path="/users" element={ <AdminProtected> <AllUsersPage /> </AdminProtected> }/>
              <Route path="/comments" element={ <AdminProtected> <AllCommentsPage /> </AdminProtected> }/>
            </Routes>
          </Router>
        </div>
      </div>
    )};
  return (
    <div className="App">
      <Router>
        <NavBar/>

        <Routes>
          <Route path='/' element={<HomePage/>}/>
          <Route path='/search' element={<SearchPage/>}/>
          <Route path='/postDetails/:id' element={<PostDetailsPage/>}/>
          <Route path='/login' element={<LoginPage/>}/>
          <Route path='/register' element={<RegisterPage/>}/>
          <Route path='/qna' element={<QnAPage/>}/>
          <Route path='/qnasession/:id' element={<Protected><QnASessionPage/></Protected>}/>
          <Route path='/about' element={<AboutPage/>}/>
          <Route path='/profile' element={<Protected> <ProfilePage/> </Protected>}/>
          <Route path='/createPost' element={<Protected> <CreatePostPage/> </Protected>}/>
          <Route path='/editProfile' element={<Protected> <EditProfilePage/> </Protected>}/>
          <Route path='/userPostDetails/:id' element={<Protected> <ProfilePostDetailsPage/> </Protected>}/>
        </Routes>
        <Footer/>
      </Router>  
    </div>
  );
}

export default App;

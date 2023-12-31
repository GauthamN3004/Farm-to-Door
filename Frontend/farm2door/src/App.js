import './App.css';
import { Route, Routes } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import 'bootstrap/dist/css/bootstrap.css';
import HomePage from './Pages/HomePage/HomePage';
import About from './Pages/About/About';
import PageNotFound from './Pages/PageNotFound/PageNotFound';
import Register from './Pages/Auth/Register/Register';
import Login from './Pages/Auth/Login/Login';
import { AuthProvider } from './Context/AuthContext';
import Dashboard from './Pages/User/Dashboard/Dashboard';
import PrivateRoute from './Routes/PrivateRoute';
import ImageUpload from './Pages/Auth/ImageUpload/ImageUpload';
import FarmerAddHarvest from './Pages/User/FarmerAddHarvest/FarmerAddHarvest';

function App() {
  return (
    <>
      <AuthProvider>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/about" element={<About />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/upload" element={<ImageUpload />} />
          {/* <Route path="/user/*" element={<PrivateRoute />} > */}
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/add-harvest" element={<FarmerAddHarvest />} />
          {/* </Route> */}
          <Route path="*" element={<PageNotFound />} />
        </Routes>
        <ToastContainer />
      </AuthProvider>
    </>
  );
}

export default App;

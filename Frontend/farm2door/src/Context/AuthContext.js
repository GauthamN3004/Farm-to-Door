import { createContext, useEffect, useContext, useState } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isLoggedIn, setLoggedIn] = useState(false);
  const [userData, setUserData] = useState(null);

  useEffect(() => {
      const data = localStorage.getItem('auth');
      if(data){
          const parseData = JSON.parse(data);
          setLoggedIn(true);
          setUserData(parseData);
      }
  }, [])

  const login = (data) => {
    setLoggedIn(true);
    setUserData(data);
  };

  const logout = () => {
    setLoggedIn(false);
    setUserData(null);
    localStorage.removeItem('auth');
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, userData, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
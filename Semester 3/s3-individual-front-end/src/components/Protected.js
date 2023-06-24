import React from 'react'
import TokenManager from '../apis/TokenManager';
import { Navigate } from 'react-router-dom';

function Protected({ children }) {
  if (TokenManager.getClaims() !== undefined) {
    return children;
  }

  return <Navigate to={"/"} />;
}

export default Protected;
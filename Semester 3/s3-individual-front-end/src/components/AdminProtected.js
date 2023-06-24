import React from "react";
import TokenManager from "../apis/TokenManager";
import { Navigate } from "react-router-dom";

function AdminProtected({ children }) {
  const claims = TokenManager.getClaims();

  if (claims?.roles?.includes("ADMIN")) {
    return children;
  }

  return <Navigate to={"/"} />;
}

export default AdminProtected;

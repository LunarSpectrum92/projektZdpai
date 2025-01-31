import { Navigate } from "react-router-dom";

const ProtectedRoute = ({ children, roles, requiredRole }) => {
  console.log("Przekazane roles:", roles);

  if (!roles || !Array.isArray(roles) || !roles.includes(requiredRole)) {
    console.log("Brak wymaganej roli:", requiredRole, "Posiadane role:", roles);
    return <Navigate to="/" replace />;
  }

  console.log("Dostęp przyznany dla roli:", requiredRole);
  return children;
};

export default ProtectedRoute;

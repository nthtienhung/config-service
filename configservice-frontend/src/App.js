import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Auth/Login';
import ConfigList from './components/Config/ConfigList';
import AddConfig from './components/Config/AddConfig';
import EditConfig from './components/Config/EditConfig';
import Layout from './components/Layout/Layout';
import PrivateRoute from './components/Auth/PrivateRoute';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Layout />}>
          <Route path="/configs" element={
            <PrivateRoute>
              <ConfigList />
            </PrivateRoute>
          } />
          <Route path="/configs/add" element={
            <PrivateRoute>
              <AddConfig />
            </PrivateRoute>
          } />
          <Route path="/configs/edit/:id" element={
            <PrivateRoute>
              <EditConfig />
            </PrivateRoute>
          } />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
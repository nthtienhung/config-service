import React from 'react';
import { Outlet } from 'react-router-dom';
import { Box, AppBar, Toolbar, Typography, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { authService } from '../../services/authService';

const Layout = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    authService.logout();
    navigate('/login');
  };

  return (
    <Box>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Config Service
          </Typography>
          <Button color="inherit" onClick={handleLogout}>
            Logout
          </Button>
        </Toolbar>
      </AppBar>
      <Box sx={{ p: 3 }}>
        <Outlet />
      </Box>
    </Box>
  );
};

export default Layout;
import React, { useState, useEffect } from 'react';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
  Box
} from '@mui/material';
import { configService } from '../../services/configService';
import { useNavigate } from 'react-router-dom';

const ConfigList = () => {
  const [configs, setConfigs] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    loadConfigs();
  }, []);

  const loadConfigs = async () => {
    try {
      const response = await configService.getConfigs();
      setConfigs(response.data.content);
    } catch (error) {
      console.error('Error loading configs:', error);
    }
  };

  const handleDelete = async (configId) => {
    if (window.confirm('Are you sure you want to delete this configuration?')) {
      try {
        await configService.deleteConfig(configId);
        loadConfigs();
      } catch (error) {
        console.error('Error deleting config:', error);
      }
    }
  };

  return (
    <Box sx={{ p: 3 }}>
      <Button
        variant="contained"
        color="primary"
        onClick={() => navigate('/configs/add')}
        sx={{ mb: 2 }}
      >
        Add New Configuration
      </Button>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Group</TableCell>
              <TableCell>Type</TableCell>
              <TableCell>Key</TableCell>
              <TableCell>Value</TableCell>
              <TableCell>Status</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {configs.map((config) => (
              <TableRow key={config.configID}>
                <TableCell>{config.group}</TableCell>
                <TableCell>{config.type}</TableCell>
                <TableCell>{config.configKey}</TableCell>
                <TableCell>{config.configValue}</TableCell>
                <TableCell>{config.status}</TableCell>
                <TableCell>
                  <Button
                    onClick={() => navigate(`/configs/edit/${config.configID}`)}
                    color="primary"
                  >
                    Edit
                  </Button>
                  <Button
                    onClick={() => handleDelete(config.configID)}
                    color="error"
                  >
                    Delete
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default ConfigList;
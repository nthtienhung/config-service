import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { 
  Box, 
  Button, 
  TextField, 
  Typography, 
  Container,
  FormControl,
  InputLabel,
  Select,
  MenuItem 
} from '@mui/material';
import { configService } from '../../services/configService';

const EditConfig = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [config, setConfig] = useState({
    group: '',
    type: '',
    configKey: '',
    configValue: '',
    status: 'ACTIVE'
  });

  useEffect(() => {
    const fetchConfig = async () => {
      try {
        const response = await configService.getConfigById(id);
        console.log("this is response data ",response.data);
        // Check the actual structure of response.data
        // const configData = response.data?.content?.[2] || response.data?.data || response;
        // const configData = response.data;
        const configData = response.data.content.find(
          (item) => item.configID === id
        );
        setConfig({
          group: configData.group || '',
          type: configData.type || '',
          configKey: configData.configKey || '', 
          configValue: configData.configValue || '',
          status: configData.status || 'ACTIVE'
        });
        
      } catch (error) {
        console.error('Error fetching config:', error);
        alert('Failed to load configuration');
        navigate('/configs');
      }
    };
    
    if (id) {
      fetchConfig();
    }
    // fetchConfig();
  }, [id, navigate]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setConfig(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const configData = {
        group: config.group,
        type: config.type,
        key: config.configKey, // Transform configKey to key
        value: config.configValue, // Transform configValue to value
        status: config.status
      };
      await configService.updateConfig(id, config);
      navigate('/configs');
    } catch (error) {
      alert('Failed to update configuration');
    }
  };

  return (
    <Container component="main" maxWidth="sm">
      <Box sx={{ mt: 8, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
        <Typography component="h1" variant="h5">
          Edit Configuration
        </Typography>
        <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            label="Group"
            name="group"
            value={config.group}
            onChange={handleChange}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            label="Type"
            name="type"
            value={config.type}
            onChange={handleChange}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            label="Key"
            name="configKey"
            value={config.configKey}
            onChange={handleChange}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            label="Value"
            name="configValue"
            value={config.configValue}
            onChange={handleChange}
          />
          <FormControl fullWidth margin="normal">
            <InputLabel>Status</InputLabel>
            <Select
              name="status"
              value={config.status}
              label="Status"
              onChange={handleChange}
            >
              <MenuItem value="ACTIVE">Active</MenuItem>
              <MenuItem value="INACTIVE">Inactive</MenuItem>
            </Select>
          </FormControl>
          <Box sx={{ mt: 3, mb: 2 }}>
            <Button type="submit" fullWidth variant="contained">Update</Button>
            <Button
              fullWidth
              variant="outlined"
              onClick={() => navigate('/configs')}
              sx={{ mt: 1 }}
            >
              Cancel
            </Button>
          </Box>
        </Box>
      </Box>
    </Container>
  );
};

export default EditConfig;
import React from 'react';
import { useNavigate } from 'react-router-dom';
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

const AddConfig = () => {
  const navigate = useNavigate();
  const [config, setConfig] = React.useState({
    group: '',
    type: '',
    configKey: '',
    configValue: '',
    status: 'ACTIVE'
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setConfig(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await configService.addConfig(config);
      navigate('/configs');
    } catch (error) {
      alert('Failed to add configuration');
      console.error(error);
    }
  };

  return (
    <Container component="main" maxWidth="sm">
      <Box
        sx={{
          marginTop: 8,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <Typography component="h1" variant="h5">
          Add New Configuration
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
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="primary"
            >
              Add Configuration
            </Button>
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

export default AddConfig;
import axios from '../utils/axiosConfig';

export const configService = {
  getConfigs: async (params) => {
    const response = await axios.get('/config/getconfig', { params });
    return response.data;
  },
  getConfigById: async (configId) => {
    try {
      // Added proper error logging
      const response = await axios.get(`config/getconfig`, {
        params: { id: configId }
      });
      // const response = await axios.get(`config/${configId}`);
      console.log('Response:', response.data); // Debug log
      return response.data;
    } catch (error) {
      console.error('Error in getConfigById:', error);
      throw error;
    }
  },

  addConfig: async (configData) => {
    const response = await axios.post('/config/', configData);
    return response.data;
  },

  updateConfig: async (configId, configData) => {
    const response = await axios.put(`/config/update/${configId}`, configData);
    return response.data;
  },

  deleteConfig: async (configId) => {
    const response = await axios.delete(`/config/delete/${configId}`);
    return response.data;
  }
};
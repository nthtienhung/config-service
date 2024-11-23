import axios from '../utils/axiosConfig';

export const configService = {
  getConfigs: async (params) => {
    const response = await axios.get('/config/getconfig', { params });
    return response.data;
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
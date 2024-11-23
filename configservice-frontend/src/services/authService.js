import axios from '../utils/axiosConfig';

export const authService = {
  login: async (username, password) => {
    try {
      const response = await axios.post('/auth/login', null, {
        params: {
          username,
          password
        },
        headers: {
          'Content-Type': 'application/json'
        }
      });
      
      if (response.data.token) {
        // Remove any existing "Bearer " prefix before storing
        const token = response.data.token.replace('Bearer ', '');
        localStorage.setItem('token', token);
      }
      return response.data;
    } catch (error) {
      console.error('Login error:', error);
      throw error;
    }
  },

  getToken: () => {
    const token = localStorage.getItem('token');
    return token;
  },

  isAuthenticated: () => {
    return !!localStorage.getItem('token');
  },

  logout: () => {
    localStorage.removeItem('token');
  }
};
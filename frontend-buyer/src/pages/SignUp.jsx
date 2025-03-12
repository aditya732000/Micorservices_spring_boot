import { useState } from 'react';
import { useSignupMutation } from '../redux/api/authApi';
import { useDispatch } from 'react-redux';
import { setCredentials } from '../redux/slices/authSlice';

export default function SignUp() {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    role: 'buyer'
  });

  const [signup] = useSignupMutation()
  const dispatch = useDispatch()


  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const result = await signup(formData).unwrap()
      dispatch(setCredentials(result.token))
      console.log(result)
    } catch (error) {
      console.log("An Error occured: ", error)
    }

  };

  return (
    <div className="flex min-h-screen items-center justify-center bg-gray-100">
      <div className="w-full max-w-md p-6 bg-white rounded-2xl shadow-lg">
        <h2 className="text-2xl font-semibold text-center text-gray-800 mb-6">User Sign Up</h2>
        <form onSubmit={handleSubmit}>
        <div className="mb-4">
            <label className="block text-gray-700 text-sm font-medium mb-2">Name</label>
            <input 
              type="text" 
              name="name" 
              value={formData.name} 
              onChange={handleChange} 
              placeholder="Enter your name" 
              className="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 focus:outline-none" 
              required 
            />
          </div>

          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-medium mb-2">Email</label>
            <input 
              type="email" 
              name="email" 
              value={formData.email} 
              onChange={handleChange} 
              placeholder="Enter your email" 
              className="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 focus:outline-none" 
              required 
            />
          </div>
          
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-medium mb-2">Password</label>
            <input 
              type="password" 
              name="password" 
              value={formData.password} 
              onChange={handleChange} 
              placeholder="Enter your password" 
              className="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 focus:outline-none" 
              required 
            />
          </div>
          <button 
            type="submit" 
            className="w-full bg-blue-500 hover:bg-blue-600 text-white font-medium py-2 rounded-lg transition duration-300"
          >
            Sign Up
          </button>
        </form>
      </div>
    </div>
  );
}

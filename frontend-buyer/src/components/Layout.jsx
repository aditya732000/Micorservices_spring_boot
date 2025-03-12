import { ShoppingCart, UserCircle } from "lucide-react";
import { useState } from "react";
import { Link, Outlet, useNavigate } from "react-router";

const Navbar = () => {
    const [query, setQuery] = useState("");
    const navigate = useNavigate()
    const handleSearchSubmit = (e) => {
        e.preventDefault()
        navigate(`/search?query=${query}`)
    }
    return (
        <nav className="bg-violet-500 p-4 shadow-lg">
            <div className="container mx-auto flex justify-between items-center">
                <Link to="/" className="text-white text-2xl font-bold">
                    MyStore
                </Link>
                <form onSubmit={handleSearchSubmit} className="relative w-1/3">
                    <input 
                        type="text" 
                        placeholder="Search products..." 
                        value={query} 
                        onChange={(e) => setQuery(e.target.value)} 
                        className="w-full p-2 rounded-full border border-gray-300 focus:outline-none focus:ring-2 focus:white text-slate-50"
                    />
                </form>
                <div className="flex space-x-8">
                    <Link to="/cart" className="relative">
                        <ShoppingCart className="text-white w-6 h-6" />
                    </Link>
                    <Link to="/order" className="relative">
                        <UserCircle className="text-white w-6 h-6" />
                    </Link>
                </div>

            </div>
        </nav>
    );
};

const Layout = () => {
    return (
        <div>
            <Navbar />
            <main className="container mx-auto p-4">
                <Outlet/>
            </main>
        </div>
    );
};

export default Layout;
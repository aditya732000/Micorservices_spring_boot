import { Outlet } from "react-router";
import Sidebar from "./Sidebar";

export default function SidebarLayout() {
  return (
    <div className="flex">
        <Sidebar/>
        <Outlet />
    </div>
  );
}

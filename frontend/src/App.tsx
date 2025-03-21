import {useEffect, useState} from "react";
import axios from "axios";
import {Route, Routes} from "react-router-dom";
import TodoApp from "./todo-app.tsx";
import Login from "./login.tsx";
import ProtectedRoutes from "./ProtectedRoutes.tsx";
import {AppUser} from "./AppUser.ts";

function App() {
    const [appUser, setAppUser] = useState<AppUser | undefined | null>(undefined)

    function getMe() {
        axios.get("/api/auth/me")
            .then((r) => setAppUser(r.data))
            .catch(e => {
                setAppUser(null);
                console.error(e);
            })
    }

    useEffect(getMe, []);

    return (
        <Routes>
            <Route path="/" element={<Login/>}/>
            <Route element={<ProtectedRoutes appUser={appUser} />}>
                <Route path="/todos" element={<TodoApp appUser={appUser}/>}/>
            </Route>
        </Routes>
    )
}

export default App

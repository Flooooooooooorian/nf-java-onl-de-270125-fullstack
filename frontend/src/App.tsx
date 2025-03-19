import {useEffect, useState} from "react";
import axios from "axios";
import {Route, Routes} from "react-router-dom";
import TodoApp from "./todo-app.tsx";
import Login from "./login.tsx";
import ProtectedRoutes from "./ProtectedRoutes.tsx";

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false)

    function getMe() {
        axios.get("/api/auth/me")
            .then(() => setIsLoggedIn(true))
            .catch(e => console.error(e))
    }

    useEffect(getMe, []);

    return (
        <Routes>
            <Route path="/" element={<Login/>}/>
            <Route element={<ProtectedRoutes isLoggedIn={isLoggedIn} />}>
                <Route path="/todos" element={<TodoApp/>}/>
            </Route>
        </Routes>
    )
}

export default App

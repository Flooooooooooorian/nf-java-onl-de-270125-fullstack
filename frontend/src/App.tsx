import {Todo} from "./Todo.ts";
import {useEffect, useState} from "react";
import axios from "axios";
import TodoColumn from "./TodoColumn.tsx";
import {allPossibleTodos} from "./TodoStatus.ts";

function App() {

    const [todos, setTodos] = useState<Todo[]>()

    function fetchTodos() {
        axios.get("/api/todo")
            .then(response => {
                setTodos(response.data)
            })
    }

    function getMe() {
        axios.get("/api/auth/me")
            .then(r => console.log(r))
            .catch(e => console.error(e))
    }

    useEffect(getMe, []);
    useEffect(fetchTodos, [])

    function login() {
        // /oauth2/authorization/github <- google, facebook whatever drin stehen
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin
        window.open(host + '/oauth2/authorization/github', '_self')
    }

    return (
        <>
            <button onClick={login}>Login with Github</button>
            <div className="page">
                <h1>All TODOs</h1>
                {
                    allPossibleTodos.map(status => {
                        const filteredTodos = todos ? todos.filter(todo => todo.status === status) : []
                        return <TodoColumn
                            status={status}
                            todos={filteredTodos}
                            onTodoItemChange={fetchTodos}
                            key={status}
                        />
                    })
                }
            </div>
        </>
    )
}

export default App

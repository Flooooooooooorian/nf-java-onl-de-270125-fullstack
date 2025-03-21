import axios from "axios";
import {useEffect, useState} from "react";
import {allPossibleTodos} from "./TodoStatus.ts";
import TodoColumn from "./TodoColumn.tsx";
import {Todo} from "./Todo.ts";
import {AppUser} from "./AppUser.ts";
import {Navigate} from "react-router-dom";

type Props = {
    appUser: AppUser | undefined | null
}

export default function TodoApp({appUser}: Readonly<Props>) {
    const [todos, setTodos] = useState<Todo[]>()

    function fetchTodos() {
        axios.get("/api/todo")
            .then(response => {
                setTodos(response.data)
            })
    }

    useEffect(fetchTodos, [])

    function logout() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin
        window.open(host + '/logout', '_self')
    }

    if (appUser === null) {
        return <Navigate to="/"/>
    }

    return (
        <>
            {
                !appUser ? <div>...loading</div> :
                    <>
                        <button onClick={logout}>Logout</button>
                        <div className="page">
                            <h1>All TODOs</h1>
                            {
                                allPossibleTodos.map(status => {
                                    const filteredTodos = todos ? todos.filter(todo => todo.status === status) : []
                                    return <TodoColumn
                                        appUser={appUser}
                                        status={status}
                                        todos={filteredTodos}
                                        onTodoItemChange={fetchTodos}
                                        key={status}
                                    />
                                })
                            }
                        </div>
                    </>
            }
        </>
    );
}
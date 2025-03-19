import axios from "axios";
import {useEffect, useState} from "react";
import {allPossibleTodos} from "./TodoStatus.ts";
import TodoColumn from "./TodoColumn.tsx";
import {Todo} from "./Todo.ts";

export default function TodoApp() {
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

    return (
        <>
            <button onClick={logout}>Logout</button>
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
    );
}
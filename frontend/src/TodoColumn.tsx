import TodoCard from "./TodoCard.tsx";
import {Todo} from "./Todo.ts";
import {TodoStatus} from "./TodoStatus.ts";
import NewTodoCard from "./NewTodoCard.tsx";
import {AppUser} from "./AppUser.ts";

type Props = {
    status: TodoStatus,
    todos: Todo[],
    onTodoItemChange: () => void,
    appUser: AppUser,
}

export default function TodoColumn(props: Readonly<Props>) {
    return (
        <div>
            <h2>{props.status}</h2>
            {
                props.todos.map(todo => <TodoCard todo={todo} key={todo.id} onTodoItemChange={props.onTodoItemChange}/>)
            }
            {
                (props.status === "OPEN") && props.appUser.role === "ADMIN" && <NewTodoCard onTodoItemChange={props.onTodoItemChange}/>
            }
        </div>
    );
}

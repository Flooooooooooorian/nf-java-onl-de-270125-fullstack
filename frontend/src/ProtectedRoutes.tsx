import {Navigate, Outlet} from "react-router-dom";
import {AppUser} from "./AppUser.ts";

type Props = {
    appUser: AppUser | undefined | null
}
export default function ProtectedRoutes({appUser}: Readonly<Props>) {

    if (appUser === undefined) {
        return <div>...Loading</div>
    }

    return appUser ? <Outlet/> : <Navigate to="/"/>
}
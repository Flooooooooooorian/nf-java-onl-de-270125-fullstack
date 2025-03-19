import {Navigate, Outlet} from "react-router-dom";

type Props = {
    isLoggedIn: boolean
}
export default function ProtectedRoutes({isLoggedIn}: Readonly<Props>){

    if(!isLoggedIn){
        return <div>...Loading</div>
    }

    return isLoggedIn ? <Outlet/> : <Navigate to="/" />
}
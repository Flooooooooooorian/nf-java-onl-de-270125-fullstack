export type AppUser = {
    id: string,
    username: string,
    avatarUrl: string,
    todoIds: string[],
    role: "ADMIN" | "USER"
}

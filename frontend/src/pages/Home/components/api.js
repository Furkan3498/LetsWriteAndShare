import http from "../../../lib/http";

export function loadUser(){
    return http.get("/api/v1/users");
}
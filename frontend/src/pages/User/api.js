import http from "../../lib/http";

export function getUser(){
    return http.get('/api/v1/users/${id}');
}
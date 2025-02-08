import http from "../../../lib/http";

export function loadUser(page = 0){
    return http.get("/api/v1/users" , {params :{page, size:3}});
}
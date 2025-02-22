import defaultProfileImage from "@/assets/profile.png"
import { Link } from "react-router-dom";




export function UserListItem({ user }) {
  return (
    <>
      <Link
        className="list-group-item list-group-item-action"
        to={`/user/${user.id}`}
        style={{ textDecoration: "none" }}
      >
        <img
          className="me-2 img-fluid rounded-circle shadow-sm"
          src={defaultProfileImage}
          width={30}
          alt="image"
        />
        {user.username}
      </Link>
    </>
  );
}
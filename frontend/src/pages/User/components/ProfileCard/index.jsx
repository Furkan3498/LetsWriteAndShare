import defaultProfileImage from "@/assets/profile.png"
import { Button } from "@/Shared/components/Button"
import { useAuthState } from "@/Shared/state/context"
import { useSelector } from "react-redux";
export function ProfileCard({user}){
  //  const authState = useAuthState();
  const authState = useSelector((store) => store.auth);

    return(
        <div className="card">


            <div className="card-header text-center">

                <img src={defaultProfileImage}
                  width="200"
                  className="img-fluid rounded-circle shadow-sm"
                  
                  />

            </div>

            <div className="card-body text-center">
                <span className="fs-3">{user.username}</span>
                {authState?.id === user.id && <Button> Edit</Button>}
            </div>

        </div>
    )

}
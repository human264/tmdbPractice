package fast.campus.netplix3.user;

import fast.campus.netplix3.NetplixUser;

import java.util.Optional;

public interface InsertUserPort {
    UserPortResponse create(CreateUser user);
}

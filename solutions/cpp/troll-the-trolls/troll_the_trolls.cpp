namespace hellmath {
    enum AccountStatus {
        troll,
        guest,
        user,
        mod
    };

    enum Action {
        read,
        write,
        remove
    };

    bool display_post(AccountStatus poster, AccountStatus viewer) {
        return poster != AccountStatus::troll || viewer == AccountStatus::troll;
    }

    bool permission_check(Action action, AccountStatus person) {
        return person == mod || action == read || ((person == user || person == troll) && action == write);
    }

    bool valid_player_combination(AccountStatus player1, AccountStatus player2) {
        switch (player1) {
            case troll: return player2 == troll;
            case user:
            case mod: return player2 == user || player2 == mod;
            default: return false;
        }
    }

    bool has_priority(AccountStatus player1, AccountStatus player2) {
        switch (player1) {
            case troll: return false;
            case guest: return player2 == troll;
            case mod: return player2 != mod;
            default:return player2 == guest || player2 == troll;
        }
    }

} // namespace hellmath

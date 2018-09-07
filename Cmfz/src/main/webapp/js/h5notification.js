var NotificationHandler = {
    isNotificationSupported: 'Notification' in window,
    isPermissionGranted: function() {
        return this.isNotificationSupported && Notification.permission === 'granted';
    },
    requestPermission: function() {
        if (!this.isNotificationSupported) {
            return;
        }

        Notification.requestPermission(function(status) {
            var permission = Notification.permission;
        });
    },
    showNotification: function(msg) {
        if (!this.isNotificationSupported) {
            return;
        }
        if (!this.isPermissionGranted()) {
            return;
        }
        var n = new Notification("New message", {
            icon: '/resources/www/images/logo_blue.png',
            body: msg
        });
        n.onshow = function() {
            setTimeout(function() {
                n.close();
            }, 5000);
        };

        n.onclick = function() {
            n.close();
        };
        n.onerror = function() {
        };
        n.onclose = function() {

        };
    }
};
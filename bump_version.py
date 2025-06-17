#!/user/bin/env python3
import sys

if len(sys.argv) != 3:
    print("Usage: bump_version.py <current_version> <update_type>", file=sys.stderr)
    sys.exit(1)

# Usage: python bump_version.py <current_version> <update_type>
version = sys.argv[1].replace('-SNAPSHOT', '')
update_type = sys.argv[2]

try:
    major, minor, patch = map(int, version.split('.'))
except ValueError:
    print("Invalid version format. Use MAJOR.MINOR.PATCH", file=sys.stderr)
    sys.exit(1)

if update_type == 'major':
    major += 1
    minor = 0
    patch = 0
elif update_type == 'minor':
    minor += 1
    patch = 0
elif update_type in ('patch', 'hotfix'):
    patch += 1
else:
    print("Invalid update type. Use major, minor, patch, or hotfix.", file=sys.stderr)
    sys.exit(1)

print(f"{major}.{minor}.{patch}")

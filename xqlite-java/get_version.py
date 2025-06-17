import sys
import xml.etree.ElementTree as ET

tree = ET.parse('./pom.xml')
ns = {'m': 'http://maven.apache.org/POM/4.0.0'}

root = tree.getroot()

version_el = root.find('m:version', ns)
if version_el is not None:
    print(version_el.text)
else:
    print('Version tag not found', file=sys.stderr)
    sys.exit(1)
